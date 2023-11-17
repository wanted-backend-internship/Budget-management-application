import { createApp } from 'vue'
// @ts-ignore
import App from './App.vue'
import { createPinia } from 'pinia';
import {VueQueryPlugin} from "vue-query";

const app = createApp(App);
app.use(createPinia()).use(VueQueryPlugin).mount('#app')
