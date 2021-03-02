<template>
    <div>
        <section>
            <v-parallax :src="imageLink.sub_main" height="600">
                <v-layout column align-center justify-center class="white--text">
                    <h1 class="white--text mb-2 display-1 text-xs-center"
                        style="font-weight: 900; text-shadow: 3px 2px #000000">An Online Collaborative Editor</h1>
                    <div class="white--text subheading mb-3 text-xs-center"
                         style="font-weight: 900; text-shadow: 2px 2px #000000">Built upon SpringBoot and Vue.js
                    </div>
                    <div class="white--text subheading mb-3 text-xs-center"
                         style="font-weight: 900; text-shadow: 2px 2px #000000">Currently supports real-time collaborative markdown editing, more yet to come. <br /> Test accounts: <br />Username: test, Password: Test123456<br />Username: test1, Password: Test123456
                    </div>
                    <template v-if="!this.$store.state.login.isLoggedIn">
                        <v-btn class="blue lighten-2 mt-5" dark large @click="$store.commit('login/showLoginDialog')">
                            Start
                        </v-btn>
                    </template>
                </v-layout>
            </v-parallax>
        </section>

        <section>
            <v-layout column wrap class="my-5" align-center>
                <v-flex xs12 sm4 class="my-3">
                    <div class="text-xs-center">
                        <h2 class="headline">Future Updates</h2>
                        <span class="subheading">
                            Multilingual support for UI; parsing for LaTeX and more text-based languages; version control and undo/redo
                        </span>
                    </div>
                </v-flex>
            </v-layout>
        </section>
    </div>
</template>

<script>
    export default {
        name: "Welcome",
        data: function() {
            return {
                imageLink: {
                    sub_main: require('../assets/background.jpeg'),
                    logo: require('../assets/welcome_logo.png'),
                    social_cover: require('../assets/social.jpg')
                }
            };
        },
        beforeMount() {
            this.$store.commit('app/setTitle', 'An Online Collaborative Editor');
            document.title = 'An Online Collaborative Editor';
        }
    };
</script>

<style scoped>

</style>