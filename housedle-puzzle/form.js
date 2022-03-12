var firebaseConfig = {
  apiKey: "AIzaSyCR6KUoo9Pd6oF-s71OKMdwEZ1ogcWxipY",
  authDomain: "hackforgood-911b0.firebaseapp.com",
  projectId: "hackforgood-911b0",
  storageBucket: "hackforgood-911b0.appspot.com",
  messagingSenderId: "906258858673",
  appId: "1:906258858673:web:161d71ea349e47e3a95890",
  measurementId: "G-21WP49ZS9B"
};

firebase.initializeApp(firebaseConfig);

const auth = firebase.auth();

function register() {
  var model = document.getElementById("signInModal");
  model.style.display = "block";
  var email = document.getElementById("email");
  var password = document.getElementById("password");
  auth.createUserWithEmailAndPassword(email.value, password.value)
    .then(() => alert("You are now registered!"))
    .catch((e) => {
      alert(e.message)
    });
  var model = document.getElementById("signInModal");
  model.style.display = "none";

}

function login() {
  var model = document.getElementById("signInModal");
  model.style.display = "block";
  var email = document.getElementById("email");
  var password = document.getElementById("password");
  auth.signInWithEmailAndPassword(email.value, password.value);
  alert("Welcome to Housedle!!");
  var model = document.getElementById("signInModal");
  model.style.display = "none";
  var intro = document.getElementById("button");
  intro.innerHTML = "Lets have fun!";

}