import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

const REST_API_URL = "http://localhost:8080/ssafit"

export const useReviewStore = defineStore('review', () => {  
    const reviewList = ref([])

    const getReviewList = (videoId) => {
        // 원래는 ajax 통신으로 db -> back에서 받은 데이터를 대입해야됨 일단은 테스트라 정적 데이터로
        
        // axios({
        //     url: `${REST_API_URL}/video/${videoId}/reviews`,
        //     method: "GET"
        // })
        // .then((response) => {
        //     reviewList.value = response.data
        //})
    }
  
    return { 
        reviewList,

    }
  })