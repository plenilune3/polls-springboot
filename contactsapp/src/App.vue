<template>
    <div id="container">
        <div class="page-header">
            <h1 class="text-center">블록체인을 활용한 전자투표</h1>
            <p>(Dynamic Component + EventBus + Axios) </p>
        </div>
        <component :is="currentView" :post="post"></component>
        <postsList :postslist="postslist"></postsList>
    </div>
</template>

<script>
    import PostsList from './components/PostsList';
    import AddPosts from './components/AddPosts';
    import UpdatePosts from './components/UpdatePosts';
    import UpdatePhoto from './components/UpdatePhoto';
    import ReadPosts from './components/ReadPosts'
    import VotePosts from './components/VotePosts'
    import CONF from './Config.js';
    import eventBus from './EventBus.js';

    export default {
        name: 'app',
        components: {PostsList, AddPosts, UpdatePosts, UpdatePhoto, ReadPosts, VotePosts},
        data: function () {
            return {
                currentView: null,
                post: {category: {id: ''}, title: '', author: '', content: '', pollAddress: ''},
                poll: {fromAddress: '', password: '', toAddress: '', pollAddress: '', vote: ''},
                posts: [],
                postslist: {
                    pageno: 1, pagesize: CONF.PAGESIZE, totalcount: 0, posts: []
                }
            }
        },
        mounted: function () {
            this.fetchPosts();
            eventBus.$on("cancel", () => {
                this.currentView = null;
            });
            eventBus.$on("addSubmit", (set) => {
                this.currentView = null;
                console.log(set.poll)
                this.addPosts(set)
            });
            eventBus.$on("updateSubmit", (post) => {
                this.currentView = null;
                this.updatePosts(post);
            });
            eventBus.$on("pollSubmit", (set) => {
                console.log(set)
                this.currentView = null;
                this.addPoll(set)
            })
            eventBus.$on("addPostsForm", () => {
                this.currentView = 'addPosts';
            });
            eventBus.$on("editPostsForm", (no) => {
                this.fetchPostsOne(no)
                this.currentView = 'updatePosts';
            });
            eventBus.$on("deletePosts", (no) => {
                this.deletePosts(no);
            });
            eventBus.$on("editPhoto", (no) => {
                this.fetchPostsOne(no)
                this.currentView = 'updatePhoto';
            });
            eventBus.$on("updatePhoto", (no, file) => {
                if (typeof file !== 'undefined') {
                    this.updatePhoto(no, file);
                }
                this.currentView = null;
            });
            eventBus.$on("readPostsForm", (no) => {
                this.fetchPostsOne(no);
                this.currentView = 'readPosts';
            });
            eventBus.$on("votePostsForm", (no) => {
                this.fetchPostsOne(no);
                this.currentView = 'votePosts'
            })
            eventBus.$on("pageChanged", (page) => {
                this.pageChanged(page);
            });
        },
        methods: {
            pageChanged: function (page) {
                this.postslist.pageno = page;
                this.fetchPosts();
            },
            fetchPosts: function () {
                this.$axios.post(CONF.FETCH, {
                    params: {
                        pageno: this.postslist.pageno,
                        pagesize: this.postslist.pagesize
                    }
                })
                    .then((response) => {
                        this.posts = response.data
                        this.postslist.posts = this.posts
                    })
                    .catch((ex) => {
                        console.log('fetchPosts failed', ex);
                        this.postslist.posts = [];
                    })
            },
            addPosts: async function (set) {
                console.log("add!!")
                await this.$axios.post(CONF.POLLCREATE, {
                    fromAddress: set.poll.fromAddress,
                    password: set.poll.password,
                    numProposals: set.poll.numProposals
                }).then((response) => {
                    console.log(response.status)
                    if (response.status == "200") {
                        console.log(response.data)
                        set.post.pollAddress = response.data
                    } else {
                        console.log('투표 생성 실패 : ' + response.data.message)
                    }
                }).catch((ex) => {
                    console.log('fail : ', ex)
                });
                await this.$axios.post(CONF.ADD, set.post)
                    .then((response) => {
                        console.log(response.status)
                        if (response.status == "200") {
                            this.postslist.pageno = 1;
                            console.log(response.data);
                            this.fetchPosts();
                        } else {
                            console.log('연락처 추가 실패 : ' + response.data.message);
                        }
                    })
                    .catch((ex) => {
                        console.log('addPosts failed : ', ex);
                    });

            },
            addPoll: async function (set) {
                await this.$axios.post(CONF.POLLVOTE, {
                    fromAddress: set.poll.fromAddress,
                    password: set.poll.password,
                    pollAddress: set.post.pollAddress,
                    vote: set.poll.vote
                }).then((response) => {
                    if (response.status == "200") {
                        console.log(response.data)
                        this.fetchPosts()
                    } else {
                        console.log('투표 실패 : ' + response.data.message)
                    }
                }).catch((ex) => {
                    console.log('addPoll failed : ', ex)
                })
            },
            updatePosts: function (post) {
                this.$axios.post(CONF.UPDATE, post)
                    .then((response) => {
                        if (response.status == "200") {
                            this.fetchPosts();
                        } else {
                            console.log('연락처 변경 실패 : ' + response.data.message);
                        }
                    })
                    .catch((ex) => {
                        console.log('updatePosts failed : ', ex);
                    })
            },
            fetchPostsOne: function (no) {
                this.$axios.post(CONF.FETCH_ONE, {
                    id: no
                }).then((response) => {
                    console.log(response.data)
                    this.post = response.data;
                    })
                    .catch((ex) => {
                        console.log('fetchPostsOne failed', ex);
                    })
            },
            deletePosts: function (no) {
                this.$axios.post(CONF.DELETE, {
                    id: no
                })
                    .then((response) => {
                        console.log(response.status)
                        if (response.status == "200") {
                            this.fetchPosts();
                        } else {
                            console.log('연락처 삭제 실패 : ' + response.data.message);
                        }
                    })
                    .catch((ex) => {
                        console.log('delete failed', ex);
                    })
            },
            updatePhoto: function (no, file) {
                var data = new FormData();
                data.append('photo', file);
                this.$axios.post(CONF.UPDATE_PHOTO.replace("${no}", no), data)
                    .then((response) => {
                        if (response.data.status === "success") {
                            this.fetchPosts();
                        } else {
                            console.log('연락처 사진 변경 실패 : ' + response.data.message);
                        }
                    })
                    .catch((ex) => {
                        console.log('updatePhoto failed', ex);
                    });
            }
        }
    }
</script>

<style scoped>
    #container {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
