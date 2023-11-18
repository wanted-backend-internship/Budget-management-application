import { createApp } from 'vue'
// @ts-ignore
import App from './App.vue'
import { createPinia } from 'pinia';
import {VueQueryPlugin} from "vue-query";
import BootstrapVue3 from 'bootstrap-vue-3';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import Router from './router';

const app = createApp(App);

app
    .use(createPinia())
    .use(BootstrapVue3)
    .use(Router)
    .use(VueQueryPlugin)
    .mount('#app')
