import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AdicionaPag from '../views/AdicionaPag.vue'

const routes = [
  {
    path: '/',
    component: Home
  },
  {
    path: '/add',
    component: AdicionaPag
  }
  ,
  {
    path: '/edit/:metodoId',
    component: AdicionaPag,
    props: true,
  }
  
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
