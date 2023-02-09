import { createApp } from 'vue'
import App from './App.vue'
import './assets/main.css'
import router from './router'
import store from './store/index'

createApp(App).use(store).use(router).mount("#app")

