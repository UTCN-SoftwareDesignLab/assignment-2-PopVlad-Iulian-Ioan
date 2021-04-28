<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-btn @click="addUser">Add User</v-btn>
      <v-btn @click="toBooks">To Books</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
      @click:row="editUser"
    ></v-data-table>
    <UserDialog
      :opened="dialogVisible"
      :user="selectedItem"
      @refresh="refreshList"
    ></UserDialog>
    <AddUserDialog
      :opened="createDialogVisible"
      :user="selectedItem"
      @refresh="refreshList"
    ></AddUserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "@/components/UserDialog";
import AddUserDialog from "@/components/AddUserDialog";
import router from "../router";

export default {
  name: "UserList",
  components: {AddUserDialog, UserDialog },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        {
          text: "Username",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Email", value: "email" },
        { text: "Roles", value: "roles" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.users = await api.users.allUsers();
    },
    toBooks(){
      router.push("/books");
    },
    editUser(user){
      this.selectedItem = user;
      this.dialogVisible = true;
    },
    addUser() {
      this.createDialogVisible = true;
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
