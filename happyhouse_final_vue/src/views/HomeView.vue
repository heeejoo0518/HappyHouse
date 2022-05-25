<template>
  <b-row>
    <b-col></b-col>
    <b-col cols="10">
      <b-carousel
        id="carousel-fade"
        style="text-shadow: 0px 0px 2px #000"
        fade
        indicators
        img-width="1024"
        img-height="480"
      >
        <b-carousel-slide
          class="slide"
          caption="Apart Information"
          :img-src="require('@/assets/apt.jpg')"
        >
        </b-carousel-slide>

        <b-carousel-slide
          class="slide"
          caption="Board"
          img-src="https://picsum.photos/1024/480/?image=20"
        >
        </b-carousel-slide>

        <b-carousel-slide
          class="slide"
          caption="Ticket Reservation"
          img-src="https://i.ibb.co/yQ10Tft/img-20201110180210-cf377892.webp"
        >
        </b-carousel-slide>
      </b-carousel>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>
<script>
export default {
  name: "HomeView",
  data() {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.slide {
  height: 80vh;
}
</style>
