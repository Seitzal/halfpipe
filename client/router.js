Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Home",
    component: httpVueLoader("static/views/Home.vue")
  },
  {
    path: "/",
    name: "About",
    component: httpVueLoader("static/views/About.vue")
  },
];

const router = new VueRouter({
  mode: 'history',
  routes
});
