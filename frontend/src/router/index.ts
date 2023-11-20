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
  {
    path: '/budgets',
    component: () => import('../views/TheBudget.vue'),
  },
  {
    path: '/budgets/total',
    component: () => import('../components/budget/SetBudgetTotal.vue'),
  },
  {
    path: '/budgets/setting',
    component: () => import('../components/budget/CreateBudgets.vue'),
  },
  {
    path: '/budgets/me',
    component: () => import('../components/budget/BudgetDetail.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
