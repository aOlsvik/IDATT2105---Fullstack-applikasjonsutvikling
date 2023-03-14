import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import CalculatorView from '../views/CalculatorView.vue'


const router = createRouter({
    history: createWebHistory(),
    routes: [
    {
        path: '/',
        name: 'Login',
        component: LoginView
    },
    {
        path: '/calculator',
        name: 'Calculator',
        component: CalculatorView,
        //meta: {
        //    requiresAuth: true
        //}
    }
    ]
})

    //router.beforeEach((to, from, next) => {
    //    if (to.matched.some(record => record.meta.requiresAuth)) {
    //      if (store.getters.isAuthenticated) {
    //        store.dispatch('fetchUserData');
    //        next();
    //      } else {
    //        next('/login');
    //      }
    //    } else {
    //      next();
    //    }
    //  });    

export default router      