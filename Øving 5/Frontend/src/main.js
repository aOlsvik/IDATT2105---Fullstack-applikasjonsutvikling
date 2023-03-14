import { createApp } from 'vue'
import App from './App.vue'
import './assets/main.css'
import store from './store/store'
import router from './routes/router'


createApp(App).use(store).use(router).mount('#app')
