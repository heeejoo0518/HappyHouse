<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">공연 정보</h3>
    <b-row>
      <b-container class="bv-example-row mt-3">
        <b-row>
          <b-col>
            <b-table
              :items="tickets"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
            >
              <template #cell(img)="data"
                ><b-img :src="data.value" width="100"></b-img
              ></template>
              <template #cell(link)="data">
                <b-button variant="outline-primary" size="md" :href="data.value"
                  >예매<br />링크</b-button
                >
              </template>
            </b-table>
          </b-col>
        </b-row>
        <div class="overflow-auto">
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
            align="center"
          ></b-pagination>
        </div>
      </b-container>
    </b-row>
  </b-container>
</template>

<script>
import { listTickets, getTotalCount } from "@/api/ticket";
export default {
  name: "TicketView",
  data() {
    return {
      tickets: [],
      perPage: 10,
      currentPage: 1,
      rows: 1,
      fields: [
        { key: "img", label: "", sortable: false, tdClass: "ml-4" },
        { key: "artist", label: "가수", sortable: true, tdClass: "ml-4" },
        {
          key: "title",
          label: "콘서트명",
          sortable: true,
          tdClass: "ml-4",
        },
        { key: "location", label: "장소", sortable: true, tdClass: "ml-4" },
        {
          key: "ticketdate",
          label: "날짜",
          sortable: true,
          tdClass: "ml-4",
        },
        {
          key: "link",
          label: "",
          sortable: false,
          tdClass: "ml-4",
        },
      ],
    };
  },
  created() {
    this.getTickets();
    this.getCount();
  },
  methods: {
    getTickets() {
      listTickets(
        ({ data }) => {
          this.tickets = data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getCount() {
      getTotalCount(
        ({ data }) => {
          this.rows = data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>
