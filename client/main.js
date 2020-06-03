const App = httpVueLoader("./static/App.vue")

const app = new Vue({
  el: '#app',
  router: router,
  store: store,
  render: h => h(App)
});
