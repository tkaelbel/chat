<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar>
        <div class="q-px-lg q-pt-xl q-mb-md">
          <div class="text-h3 q-pt-xl">Just Chat</div>
          <div class="text-subtitle1">{{ todaysDate }}</div>
          <q-img src="../statics/mountains.png" class="header-image absolute-top"/>
        </div>
        
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { date } from 'quasar'

export default {
  name: "MainLayout",
  data() {
    return {};
  },
  computed: {
    todaysDate(){
      const today = Date.now();
      return date.formatDate(today, 'dddd D MMMM');
    } 
  }
};
</script>

<style lang="scss">
.header-image {
  height: 100%;
  z-index: -1;
}
</style>
