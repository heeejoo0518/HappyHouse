"use strict";(self["webpackChunkhappyhouse"]=self["webpackChunkhappyhouse"]||[]).push([[600],{600:function(t,e,a){a.r(e),a.d(e,{default:function(){return k}});var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("b-container",{staticClass:"bv-example-row mt-3 text-center"},[a("h3",{staticClass:"underline-hotpink"},[t._v("공연 정보")]),a("b-row",[a("b-container",{staticClass:"bv-example-row mt-3"},[a("b-row",[a("b-col",[a("b-table",{attrs:{items:t.tickets,fields:t.fields,"per-page":t.perPage,"current-page":t.currentPage},scopedSlots:t._u([{key:"cell(img)",fn:function(t){return[a("b-img",{attrs:{src:t.value,width:"100"}})]}},{key:"cell(link)",fn:function(e){return[a("b-button",{attrs:{variant:"outline-primary",size:"md",href:e.value}},[t._v("예매"),a("br"),t._v("링크")])]}}])})],1)],1),a("div",{staticClass:"overflow-auto"},[a("b-pagination",{attrs:{"total-rows":t.rows,"per-page":t.perPage,"aria-controls":"my-table",align:"center"},model:{value:t.currentPage,callback:function(e){t.currentPage=e},expression:"currentPage"}})],1)],1)],1)],1)},l=[],r=a(6927),s=(0,r.b)();function i(t,e){s.get("/ticket").then(t).catch(e)}function o(t,e){s.get("/ticket/count").then(t).catch(e)}var c={name:"TicketView",data:function(){return{tickets:[],perPage:10,currentPage:1,rows:1,fields:[{key:"img",label:"",sortable:!1,tdClass:"ml-4"},{key:"artist",label:"가수",sortable:!0,tdClass:"ml-4"},{key:"title",label:"콘서트명",sortable:!0,tdClass:"ml-4"},{key:"location",label:"장소",sortable:!0,tdClass:"ml-4"},{key:"ticketdate",label:"날짜",sortable:!0,tdClass:"ml-4"},{key:"link",label:"",sortable:!1,tdClass:"ml-4"}]}},created:function(){this.getTickets(),this.getCount()},methods:{getTickets:function(){var t=this;i((function(e){var a=e.data;t.tickets=a}),(function(t){console.log(t)}))},getCount:function(){var t=this;o((function(e){var a=e.data;t.rows=a}),(function(t){console.log(t)}))}}},u=c,b=a(1001),f=(0,b.Z)(u,n,l,!1,null,null,null),k=f.exports}}]);
//# sourceMappingURL=600-legacy.6f4fd090.js.map