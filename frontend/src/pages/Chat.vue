<template>
  <q-page class="flex flex-center">
    <!-- <div class="q-pa-md q-gutter-sm"> -->
      <div v-if="!user" class="userForm">
        <h2>Just Chat</h2>
        <q-form @submit="activeUser" class="q-gutter-md">
          <q-input
            filled
            v-model="userName"
            label="Your name *"
            lazy-rules
            :rules="[
              (val) => (val && val.length > 0) || 'Please type something',
            ]"
          />

          <div>
            <q-btn
              v-if="userName !== ''"
              label="Start chatting"
              type="submit"
              color="primary"
            />
          </div>
        </q-form>
      </div>
      <message v-else :user="user"></message>
    <!-- </div> -->
  </q-page>
</template>

<script>
import { api } from "boot/axios";

export default {
  data() {
    return {
      user: null,
      userName: null,
    };
  },

  methods: {
    activeUser(e) {
      e.preventDefault();
      if (this.userName) {
        return api.post(`/users`, { name: this.userName }).then((response) => {
          this.user = response ? response.data : null;
          this.userName = "";
        });
      }
    },
  },
};
</script>

<style lang="scss">
.userForm {
  padding: 24px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  align-content: center;

  form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    align-content: center;
    input {
      font-size: 22px;
    }
    button {
      vertical-align: middle;
      padding: 10px 20px;
      text-align: center;
      text-transform: uppercase;
    }
  }
}
</style>
