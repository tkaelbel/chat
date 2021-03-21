<template>
  <div class="message-component">
    <div class="message-container">
      <div class="messages">
        <div
          v-for="message in messages"
          v-bind:key="message"
          v-bind:class="
            isCurrentUser(message.userId) ? 'current-user' : 'other-user'
          "
        >
          <div class="bubble">
            <div v-if="!isCurrentUser(message.userId)" class="user-name">
              {{ message.userName }}
            </div>
            <div class="message-text">
              <span class="margin-right-10">{{ message.text }}</span>
              <span v-if="isToday(message.date)" class="message-date">
                {{ getDateWithFormat(message.date, true) }}
              </span>
            </div>
            <div v-if="!isToday(message.date)" class="message-date">
              {{ getDateWithFormat(message.date, false) }}
            </div>
          </div>
        </div>
      </div>
    </div>

    <q-form @submit="sendMessage" class="q-gutter-md message-form">
      <q-input
        bottom-slots
        v-model="message"
        label="Write a message"
        counter
        maxlength="200"
        :dense="dense"
        type="text"
      >
        <template v-slot:append>
          <q-icon
            v-if="text !== ''"
            name="close"
            @click="text = ''"
            class="cursor-pointer"
          />
        </template>

        <template v-slot:after>
          <q-btn round dense flat icon="send" />
        </template>
      </q-input>
    </q-form>
  </div>
</template>

<script>
import { api } from "boot/axios";

export default {
  data() {
    return {
      messages: null,
      user: null
    };
  },
  methods: {
    created() {
      api.get("/messages").then((response) => {
        this.messages = response.data.map((message) => ({
          ...message,
          date: new Date(message.date),
        }));
      });
    },
    
    sendMessage(event) {
      event.preventDefault();
      if (this.message !== "") {
        const body = {
          text: this.message,
          userId: this.user.id,
        };
        return api
          .post('/messages/new', body)
          .then(() => (this.message = ""));
      }
    },

    isCurrentUser(userId) {
      return userId === this.user.id;
    }
  },
};
</script>

<style lang="scss">
.message-component {
  height: 500px;
  width: 100%;

  .message-container {
    padding: 20px;
    height: calc(100% - 75px);
    overflow-y: scroll;
    .messages {
      .current-user {
        display: flex;
        justify-content: flex-end;
        align-items: center;
      }
      .other-user {
        display: flex;
        justify-content: flex-start;
        align-items: center;
      }
      .bubble {
        box-shadow: -1px 1px 0px #ccd6d8;
        padding: 10px;
        border: solid 1px #9f9f9f;
        border-radius: 10px;
      }
      .current-user,
      .other-user {
        margin-bottom: 20px;
      }
      .user-name,
      .message-date {
        color: #949494;
        font-size: 12px;
      }
      .user-name,
      .message-text {
        margin-bottom: 5px;
      }
      .message-text {
        font-size: 16px;
        display: flex;
        flex-direction: row;
        align-items: flex-end;
        align-content: flex-end;
        .margin-right-10 {
          margin-right: 10px;
        }
      }
    }
  }

  .message-form {
    width: 100%;
    height: 75px;
    display: flex;
    align-items: center;
  }
}
::-webkit-scrollbar {
  width: 8px;
}
::-webkit-scrollbar-button {
  width: 8px;
  height: 5px;
}
::-webkit-scrollbar-track {
  background: #eee;
  border: thin solid lightgray;
  box-shadow: 0 0 3px #dfdfdf inset;
  border-radius: 10px;
}
::-webkit-scrollbar-thumb {
  background: #999;
  border: thin solid gray;
  border-radius: 10px;
}
::-webkit-scrollbar-thumb:hover {
  background: #7d7d7d;
}
</style>