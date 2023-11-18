import { createWebHistory, createRouter } from 'vue-router';

const routes = [
  {
    path: '/register',
    component: () => import('../views/TheRegister.vue'),
  },
  {
    path: '/login',
    component: () => import('../views/TheLogin.vue'),
  },
  {
    path: '/dash-board',
    component: () => import('../views/DashBoard.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
