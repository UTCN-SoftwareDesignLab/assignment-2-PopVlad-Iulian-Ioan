<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ "Add User" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="user.email" label="Email" />
          <v-text-field v-model="user.password" label="Password" />
          <v-text-field v-model="user.username" label="Username" />
        </v-form>
        <v-card-actions>
          <v-btn @click="addUser">
            {{ "Add" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "AddUserDialog",
  props: {
    user: Object,
    opened: Boolean,
    userHeader: [
      {
        text: "Email",
        align: "start",
        value: "email",
      },
      {
        text: "UserName",
        value: "username",
      },
      {
        text: "Password",
        value: "password",
      },
    ],
  },
  methods: {
    addUser() {
      api.users
        .create({
          email: this.user.email,
          password: this.user.password,
          username: this.user.username,
        })
        .then(() => this.$emit("refresh"));
    },
  },
};
</script>

<style scoped></style>
