<template>
    <postsForm mode="vote" :post="post"></postsForm>
</template>

<script>
    import PostsForm from './PostsForm.vue'

    export default {
        name: "votePosts",
        components: {PostsForm},
        props: ['post']
    }
</script>


