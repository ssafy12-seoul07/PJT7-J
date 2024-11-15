import ReviewList from '@/components/ReviewList.vue'
import VideoDetail from '@/components/videoDetail.vue'
import VideoList from '@/components/videoList.vue'
import MainPage from '@/views/MainPage.vue'
import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainPage,
      children: [
        {
          path: '',
          name: 'videoList',
          component: VideoList
        },
        {
          path: ':id',
          name: 'videoDetail',
          component: VideoDetail,
          children: [
            {
              path: '',
              name: 'reviewList',
              component: ReviewList
            }
          ]
        }
      ]
    },
    {

    }
  ],
})

export default router
