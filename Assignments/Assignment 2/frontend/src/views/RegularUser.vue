<template>
  <v-card>
    <v-card-title>
      Books
      <v-spacer></v-spacer>
    </v-card-title>
          <v-text-field
        v-model="title"
        label="title"
        single-line
        hide-details
      ></v-text-field>
            <v-text-field
        v-model="author"
        label="author"
        single-line
        hide-details
      ></v-text-field>
            <v-text-field
        v-model="genre"
        label="genre"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="search">search</v-btn>
      <v-btn @click="clear">clear</v-btn>
    <v-data-table
      :headers="headers"
      :items="books"
      :search="search"
      @click:row="buyBook"
    ></v-data-table>
    <SellBook
      :opened="dialogVisible"
      :book="selectedItem"
      @refresh="refreshList"
    ></SellBook>
  </v-card>
</template>

<script>
import api from "../api";
import SellBook from "@/components/SellBook";
export default {
  name: "RegularUser",
  components: { SellBook },
  data() {
    return {
      books: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "author", value: "author" },
        { text: "genre", value: "genre" },
        { text: "price", value: "price" },
        { text: "quantity", value: "quantity" },
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    buyBook(book) {
      this.selectedItem = book;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.books = await api.books.allBooks();
    },
  },
  created() {
    this.refreshList();
  },
  search(){
   
  },
  clear() {
    this.refreshList();
  },
};
</script>

<style scoped></style>