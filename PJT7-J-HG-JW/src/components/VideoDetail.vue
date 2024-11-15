<template>
    <div>
        <div class="video">
            <h4>{{ store.video.title }}</h4>
            <div class="info">
                <span>{{ store.video.part }}</span>
                <span> | </span>
                <span>{{ store.video.channelName }}</span>
            </div>
            <div class="img">
                <img :src="'https://img.youtube.com/vi/' + store.video.id + '/0.jpg'" alt="youtube thumbnail">
            </div>
        </div>
        <div class="review">
            <router-view></router-view>
        </div>
    </div>
</template>

<script setup>
    import { useRoute } from 'vue-router'
    import { useVideoStore } from '@/stores/videoStore';
    import { ref, onMounted } from 'vue'

    const route = useRoute()
    const store = useVideoStore()

    // uri parameter에서 id 가져오기
    const id = route.params.id

    // mount 될 때 id를 통해서 store의 video 데이터 값 넣어두기
    onMounted(() => {
        store.getVideoById(id)        
    })

</script>

<style scoped>
    .video {
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;
    }

    .img {
        margin-top: 1rem;
    }

</style>