// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
//import App from './App'
import App from './AppAxiosTest.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'
import ES6Promise from 'es6-promise'
import axios from 'axios'

ES6Promise.polyfill()

Vue.prototype.$axios = axios;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
