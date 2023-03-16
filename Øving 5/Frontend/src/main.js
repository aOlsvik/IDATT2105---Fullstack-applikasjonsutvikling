import { createApp } from 'vue'
import {createPinia } from 'pinia'
import App from './App.vue'
import './assets/main.css'
import router from './routes/router'
import piniaPluginPersistedState from "pinia-plugin-persistedstate"


const pinia = createPinia();
pinia.use(piniaPluginPersistedState)

createApp(App).use(pinia).use(router).mount('#app')
