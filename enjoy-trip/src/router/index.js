import Vue from "vue";
import VueRouter from "vue-router";
import AppHome from "@/views/AppHome";
import store from "@/store/index.js";

Vue.use(VueRouter);

const authUserOnly = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    if (from.name !== "userLogin") {
      router.push({ name: "userLogin" });
    }
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: AppHome,
  },
  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardList"
          ),
      },
      {
        path: "write",
        name: "boardWrite",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardWrite"
          ),
        beforeEnter: authUserOnly,
      },
      {
        path: "view/:articleno",
        name: "boardDetail",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardDetail"
          ),
        beforeEnter: authUserOnly,
      },
      {
        path: "modify",
        name: "boardModify",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardModify"
          ),
      },
    ],
  },
  {
    path: "/attraction",
    name: "attraction",
    component: () =>
      import(/* webpackChunkName: "attraction" */ "@/views/AppAttraction"),
    redirect: "/attraction/list",
    children: [
      {
        path: "list",
        name: "attractionList",
        component: () =>
          import(
            /* webpackChunkName: "AttractionList" */ "@/components/attraction/AttractionList"
          ),
        beforeEnter: authUserOnly,
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppUser"),
    children: [
      {
        path: "regist",
        name: "userRegist",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/user/UserRegist"
          ),
      },
      {
        path: "login",
        name: "userLogin",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "userMypage",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/user/UserMypage"
          ),
        beforeEnter: authUserOnly,
      },
    ],
  },
  {
    path: "/trip",
    name: "trip",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppTrip"),
    redirect: "/trip/list",
    children: [
      {
        path: "list",
        name: "tripList",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/trip/TripList"),
        beforeEnter: authUserOnly,
      },
      {
        path: "detail/:tripId",
        name: "tripDetail",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/trip/TripDetail"
          ),
        beforeEnter: authUserOnly,
      },
    ],
  },
  {
    path: "/404",
    name: "NotFound404",
    component: () =>
      import(/* webpackChunkName: "board" */ "@/views/App404Error"),
  },
  {
    path: "*",
    redirect: "404",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
