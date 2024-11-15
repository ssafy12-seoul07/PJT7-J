<template>
    <div>
        <h3>영상 목록</h3>

        <table>
            <thead>
                <tr>
                    <th>제목</th>
                    <th>부위</th>
                    <th>채널 명</th>
                    <th>영상 url</th>                    
                </tr>
            </thead>            
            <tbody>
                <!-- v-if로 다양한 출력 하는 거 같은데... 어캐함..? routerview는 prop안될텐데...? -->
                <tr v-for="video in store.videoList" :key="video.id">
                    <td>
                        <router-link :to="`/${video.id}`">{{ video.title }}</router-link>
                    </td>
                    <td>{{ video.part }}</td>
                    <td>{{ video.channelName }}</td>
                    <td>{{ video.url }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>  
    import { useVideoStore } from '@/stores/videoStore';    
    import { onMounted } from 'vue'

    const store = useVideoStore()
    
    // mount 될 때 store에 있는 videoList에 값 넣어주기
    onMounted(() => {
        store.getVideoList()
    })

</script>

<style scoped>
    table {
        border-collapse: separate;
        border-spacing: 0 1rem;
    }

    a {
        text-decoration: none;
        color: black;
    }

    th {
        border-bottom: solid black 1px;
    }

    tr {
        text-align: center;        
    }

    td {
        min-width: 5em;
    }
</style>