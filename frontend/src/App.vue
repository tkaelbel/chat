<template>
  <div id="q-app">
    <router-view />
  </div>
</template>


<script>
import Vue from 'vue'
import message from './components/Message.vue'

Vue.component('message', message);

export default {
  name: 'App'
}
</script>
