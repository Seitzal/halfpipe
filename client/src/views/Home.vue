<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <p>Landing Page Placeholder</p>
    <p>{{motd}}</p>
  </div>
</template>

<script>
import api from '@/api.js'

export default {
  name: 'Home',
  data: function() { return {
    motd: ""
  }},
  methods: {
    getMotd: function() {
      api.getMotd((data) => {this.motd = data;});
    }
  },
  mounted: function() {
    this.getMotd();
  }
}
</script>
