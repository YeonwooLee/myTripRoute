import Vue from "vue";
import Vuetify from "vuetify/lib";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";

library.add(fas, far, fab); // Include needed icons
Vue.component("font-awesome-icon", FontAwesomeIcon); // Register component globally

Vue.use(Vuetify);

export default new Vuetify({
  icons: {
    iconfont: "faSvg",
  },
});
