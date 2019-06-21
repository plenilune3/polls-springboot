<template>
    <postsForm mode="read" :post="post"></postsForm>
</template>

<script>
    import PostsForm from './PostsForm.vue'

    export default {
        name: "readPosts",
        components: {PostsForm},
        props: ['post']
    }
</script>

