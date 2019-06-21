<template>
    <div class="modal">
        <div class="form" @keyup.esc="cancelEvent">
            <h3 class="heading">:: {{headingText}}</h3>
            <div v-if="mode=='update'" class="form-group">
                <label>게시글번호</label>
                <input type="text" name="id" class="long" disabled v-model="post.id"/>
            </div>

            <div class="form-group">
                <label>분류</label>
                <input type="text" name="category" class="long" v-model="post.category.id"
                       ref="name" placeholder="분류를 입력하세요"/>
            </div>
            <div class="form-group">
                <label>제목</label>
                <input type="text" name="title" class="long" v-model="post.title"
                       placeholder="제목 입력하세요"/>
            </div>
            <div class="form-group">
                <label>작성자</label>
                <input type="text" name="author" class="long" v-model="post.author"
                       placeholder="작성자 입력하세요"/>
            </div>
            <div class="form-group">
                <label>내용</label>
                <input type="text" name="content" class="long" v-model="post.content"
                       placeholder=" 입력하세요"/>
            </div>
            <div class="form-group" v-if="mode=='add'">
                <label>후보 개수</label>
                <input type="text" name="numProposal" class="long" v-model="poll.numProposals"
                       placeholder=" 입력하세요"/>
            </div>
            <div class="form-group">
                <label>지갑주소</label>
                <input type="text" name="address" class="long" v-model="poll.fromAddress"
                       placeholder=" 입력하세요"/>
            </div>
            <div v-if="mode=='vote'" class="form-group">
                <label>투표 주소</label>
                <input type="text" name="id" class="long" disabled v-model="post.pollAddress"/>
            </div>
            <div v-if="mode=='vote'" class="form-group">
                <label>선택지</label>
                <input type="text" name="id" class="long" v-model="poll.vote"/>
            </div>
            <div class="form-group">
                <label>비밀번호</label>
                <input type="text" name="address" class="long" v-model="poll.password"
                       placeholder=" 입력하세요"/>
            </div>
            <div class="form-group">
                <div>&nbsp;</div>
                <input type="button" class="btn btn-primary"
                       v-bind:value="btnText" @click="submitEvent()"/>
                <input type="button" class="btn btn-primary"
                       value="취 소" @click="cancelEvent()"/>
            </div>
        </div>
    </div>
</template>

<script>
    import eventBus from '../EventBus.js';

    export default {
        name: "postsForm",
        props: {
            mode: {type: String, default: 'add'},
            post: {
                type: Object,
                default: function () {
                    return {category: {id: ''}, name: '', title: '', author: '', content: ''}
                }
            },
            poll: {
                type: Object,
                default: function () {
                    return {
                        fromAddress: '', password: '', toAddress: '', pollAddress: '', vote: '',
                        numProposals: '', winningProposal: ''
                    }
                }
            },
            set: {
                type: Object,
                default: function () {
                    return {post: '', poll: ''}
                }
            }
        },
        mounted: function () {
            this.$refs.name.focus()
        },
        computed: {
            btnText: function () {
                if (this.mode == 'add') {
                    return '추 가';
                } else if (this.mode == 'update') {
                    return '수 정';
                } else if (this.mode == 'vote') {
                    return '투 표'
                } else return '확 인';
            },
            headingText: function () {
                if (this.mode == 'add') {
                    return '투표 게시';
                } else if (this.mode == 'update') {
                    return '투표 변경'
                } else if (this.mode == 'vote') {
                    return '투표 하기'
                } else return '확 인';
            }
        },
        methods: {
            submitEvent: function () {
                if (this.mode == "update") {
                    eventBus.$emit("updateSubmit", this.post)
                } else if (this.mode == "add") {
                    this.set.post = this.post
                    this.set.poll = this.poll
                    eventBus.$emit("addSubmit", this.set)
                } else if (this.mode == "vote") {
                    this.set.post = this.post
                    this.set.poll = this.poll
                    eventBus.$emit("pollSubmit", this.set)
                }
            },
            cancelEvent: function () {
                eventBus.$emit("cancel");
            }
        }
    }
</script>

<style scoped>
    .modal {
        display: block;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgb(0, 0, 0);
        background-color: rgba(0, 0, 0, 0.4);
    }

    .form {
        background-color: white;
        margin: 100px auto;
        max-width: 400px;
        min-width: 200px;
        font: 13px "verdana";
        padding: 10px 10px 10px 10px;
    }

    .form div {
        padding: 0;
        display: block;
        margin: 10px 0 0 0;
    }

    .form label {
        text-align: left;
        margin: 0 0 3px 0;
        padding: 0px;
        display: block;
        font-weight: bold;
    }

    .form input, textarea, select {
        box-sizing: border-box;
        border: 1px solid #BEBEBE;
        padding: 7px;
        margin: 0px;
        outline: none;
    }

    .form .long {
        width: 100%;
    }

    .form .button {
        background: #2B798D;
        padding: 8px 15px 8px 15px;
        border: none;
        color: #fff;
    }

    .form .button:hover {
        background: #4691A4;
    }

    .form .heading {
        background: #33A17F;
        font-weight: 300;
        text-align: left;
        padding: 20px;
        color: #fff;
        margin: 5px 0 30px 0;
        padding: 10px;
        min-width: 200px;
        max-width: 400px;
    }
</style>
