<template>
  <div class="home">
    <img alt="Vue logo" src="./static/assets/logo.png">
    <p>Landing Page Placeholder</p>
    <p>Message of the Day: <b>{{$store.state.motd}}</b></p>
  </div>
</template>

<script>
module.exports = {
  methods: {
    getMotd: function() {
      api.getMotd((data) => store.commit("setMotd", data));
    }
  },
  mounted: function() {
    this.getMotd();
  }
}
</script>