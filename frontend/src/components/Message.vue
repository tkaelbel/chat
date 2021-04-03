<template>
    <div class="message-component">
      <div class="message-container">
        <div class="messages">
          <div
            v-for="message in messages"
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
                  {{ formattedDate(message.date, true) }}
                </span>
              </div>
              <div v-if="!isToday(message.date)" class="message-date">
                {{ formattedDate(message.date, false) }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <q-form class="message-form">
        <div class="message-input">
          <q-input
            bottom-slots
            v-model="message"
            label="Write a message"
            counter
            maxlength="200"
            type="text"
          >
            <template v-slot:append>
              <q-icon
                v-if="message !== ''"
                name="close"
                @click="message = ''"
                class="cursor-pointer"
              />
            </template>

            <template v-slot:after>
              <q-btn
                type="submit"
                round
                flat
                icon="send"
                @click="sendMessage"
              />
            </template>
          </q-input>
        </div>
      </q-form>
    </div>
</template>

<script>
import { api } from "boot/axios";
import { Stomp } from "@stomp/stompjs";

const socket = "ws://localhost:8000/socket";

let ws = undefined;

export default {
  name: "message",
  props: ["user"],
  data() {
    return {
      messages: null,
      message: null,
    };
  },
  mounted() {
    this.connect();
  },
  created() {
    api.get("/messages").then((response) => {
      this.messages = response.data.map((message) => ({
        ...message,
        date: new Date(message.date),
      }));
    });
  },
  destroyed() {
    this.disconnect();
  },
  methods: {
    sendMessage() {
      if (this.message !== "") {
        const body = {
          text: this.message,
          userId: this.user.name,
        };
        return api.post("/messages/new", body).then(() => (this.message = ""));
      }
    },

    isCurrentUser(userId) {
      return userId === this.user.id;
    },

    isToday(date) {
      const today = new Date();
      return (
        date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear()
      );
    },

    formattedDate(date, isToday) {
      if (isToday) {
        return new Intl.DateTimeFormat("en-GB", {
          hour: "2-digit",
          minute: "numeric",
        }).format(date);
      }
      return new Intl.DateTimeFormat("en-GB", {
        year: "numeric",
        month: "long",
        day: "numeric",
      }).format(date);
    },

    connect() {
      const webSocket = new WebSocket(socket);
      ws = Stomp.over(webSocket);
      ws.connect({}, () => {
        ws.subscribe("/chat", (frame) => {
          const message = JSON.parse(frame.body);
          const formatedMessage = { ...message, date: new Date(message.date) };
          this.messages = [...this.messages, formatedMessage];
        });
      });
    },

    disconnect() {
      if (ws != null) {
        ws.ws.close();
      }
    },
  },
};
</script>

<style lang="scss">
.message-component {
  height: 70vh;
  width: 100%;
  border: 1px solid #7d7d7d;

  .message-container {
    padding: 20px;
    height: calc(100% - 77px);
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
    display: flex;
    align-items: center;
    padding-left: 5px;
    background-color: #dfdfdf;
  }

  .message-input {
    width: 100%;
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