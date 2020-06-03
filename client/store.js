Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    motd: "Test"
  },
  mutations: {
    setMotd: (state, motd) => {
      state.motd = motd;
    }
  },
  actions: {
  },
  modules: {
  }
});
