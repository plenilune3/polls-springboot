<template>
    <postsForm mode="update" :post="post"/>
</template>

<script>
    import PostsForm from './PostsForm.vue';

    export default {
        name: "updatePosts",
        components: {PostsForm},
        props: ['post']
    }
</script>

