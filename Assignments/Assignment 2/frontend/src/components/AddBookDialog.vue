<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ "Add book" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="book.title" label="Title" />
          <v-text-field v-model="book.author" label="Author" />
          <v-text-field v-model="book.genre" label="Genre" />
          <v-text-field v-model="book.price" label="Price" />
          <v-text-field v-model="book.quantity" label="Quantity" />
        </v-form>
        <v-card-actions>
          <v-btn @click="addBook"> {{ "Add  Book" }} </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "AddBookDialog",
  props: {
    book: Object,
    opened: Boolean,
  },
  methods: {
    addBook() {
      api.books
        .create({
          title: this.book.title,
          author: this.book.author,
          genre: this.book.genre,
          price: this.book.price,
          quantity: this.book.quantity,
        })
        .then(() => this.$emit("refresh"));
    },
  },
};
</script>

<style scoped></style>
