<template>
    <div>
        <p class="addnew">
            <button class="btn btn-primary" @click="addPosts()">
                새로운 연락처 추가하기
            </button>
        </p>
        <div id="example">
            <table id="list" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>분류</th>
                    <th>제목</th>
                    <th>게시자</th>
                    <th>게시일</th>
                    <th>투표/편집/삭제</th>
                </tr>
                </thead>
                <tbody id="posts">
                <tr v-for="post in postslist.posts" :key="post.id">
                    <td>{{post.category.id}}</td>
                    <td class="block" @click="readPosts(post.id)">{{post.title}}</td>
                    <td>{{post.author}}</td>
                    <td>{{post.createdDate}}</td>
                    <td>
                        <button class="btn btn-primary"
                                @click="votePosts(post.id)">투표
                        </button>
                        <button class="btn btn-primary"
                                @click="editPosts(post.id)">편집
                        </button>
                        <button class="btn btn-primary"
                                @click="deletePosts(post.id)">삭제
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <paginate ref="pagebuttons"
                  :page-count="totalpage"
                  :page-range="7"
                  :margin-pages="3"
                  :click-handler="pageChanged"
                  :prev-text="'이전'"
                  :next-text="'다음'"
                  :container-class="'pagination'"
                  :page-class="'page-item'">
        </paginate>
    </div>
</template>

<script>
    import eventBus from '../EventBus';
    import Paginate from 'vuejs-paginate';

    export default {
        name: 'postsList',
        components: {Paginate},
        props: ['postslist'],
        computed: {
            totalpage: function () {
                return Math.floor((this.postslist.totalcount - 1) / this.postslist.pagesize) + 1;
            }
        },
        watch: {
            ['postslist.pageno']: function () {
                this.$refs.pagebuttons.selected = this.postslist.pageno - 1;
            }
        },
        methods: {
            pageChanged: function (page) {
                eventBus.$emit("pageChanged", page);
            },
            addPosts: function () {
                eventBus.$emit("addPostsForm");
            },
            readPosts: function (no) {
                eventBus.$emit("readPostsForm", no)
            },
            editPosts: function (no) {
                eventBus.$emit("editPostsForm", no)
            },
            votePosts: function (no) {
                eventBus.$emit("votePostsForm", no)
            },
            deletePosts: function (no) {
                if (confirm("정말로 삭제하시겠습니까?") == true) {
                    eventBus.$emit('deletePosts', no);
                }
            },
            editPhoto: function (no) {
                eventBus.$emit("editPhoto", no);
            }
        }
    }
</script>

<style scoped>
    .addnew {
        margin: 10px auto;
        max-width: 1000px;
        min-width: 1000px;
        padding: 10px 0px 0px 0px;
        text-align: left;
    }

    #example {
        margin: 10px auto;
        max-width: 1000px;
        min-width: 1000px;
        padding: 0px;
        position: relative;
        font: 13px "verdana";
    }

    #example .long {
        width: 100%;
    }

    #example .short {
        width: 100%;
    }

    #example input, textarea, select {
        box-sizing: border-box;
        border: 1px solid #BEBEBE;
        padding: 7px;
        margin: 0px;
        outline: none;
    }

    #list {
        width: 1000px;
        font: 13px "verdana";
    }

    #list thead tr {
        color: yellow;
        background-color: purple;
    }

    #list th:nth-child(5n+1), #list td:nth-child(5n+1) {
        width: 50px;
    }

    #list th:nth-child(5n+2), #list td:nth-child(5n+2) {
        width: 350px;
    }

    #list th:nth-child(5n+3), #list td:nth-child(5n+3) {
        width: 100px;
    }

    #list th:nth-child(5n+4), #list td:nth-child(5n+4) {
        width: 100px;
    }

    #list th:nth-child(5n), #list td:nth-child(5n) {
        width: 150px;
    }

    #list th {
        padding: 10px 5px 10px 5px;
    }

    #list tr {
        border-bottom: solid 1px black;
    }

    #list td, #list th {
        text-align: center;
        vertical-align: middle;
    }

    img.thumbnail {
        width: 48px;
        height: 48px;
        margin-top: auto;
        margin-bottom: auto;
        display: block;
        cursor: pointer;
    }
</style>
