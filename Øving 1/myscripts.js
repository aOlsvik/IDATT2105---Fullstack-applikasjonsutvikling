var programmingLanguages = ["Python","C++","TypeScript","Ruby","Ada","Cobol","PowerShell","LISP","COBOL","Erlang","Java","PHP","MATLAB","Scala","Lua","Julia","SQL","Ballerina","Speakeasy","Ada","JavaScript","R","Kotlin","Visual Basic","Abap","Haskell","Clojure","FORTRAN","Simula","Eiffel","C#","Objective-C","Go","Rust","Groovy","Delphi","Elixir","BASIC","Smalltalk","Rebol","C","Swift","VBA","Dart","Perl","Elm","Pascal",,"Alice","Prolog","Scratch"]

const plusbutton = document.getElementById("plus-button")
const minusbutton = document.getElementById("minus-button")
const toggleImage = document.getElementById("toggle-image")
const listOfWords = document.getElementById("list-of-words")
const displaywords = document.getElementById("display-words")
var wordlist = listOfWords.getElementsByTagName('li')

plusbutton.addEventListener('click',() => document.getElementById("number").textContent++)
minusbutton.addEventListener('click',() => document.getElementById("number").textContent--)
toggleImage.addEventListener('click', showHideImage)
displaywords.addEventListener('click', displayWords)

function showHideImage() {
    var img = document.getElementById("css-logo");
    if (img.style.display == "none") {
      img.style.display = "block";
      toggleImage.textContent = "Hide image"
    } else {
      img.style.display = "none";
      toggleImage.textContent = "Show image"
    }
  }

  function generateKeywords(){
    let list = []
    for(var i = 0; i<5; i++){
        var language = programmingLanguages[Math.floor(Math.random()*programmingLanguages.length)]
        while(list.includes(language)){
          language = programmingLanguages[Math.floor(Math.random()*programmingLanguages.length)]
        }
        list.push(language)
    }
    return list
  }

  function displayWords(){
    let list = generateKeywords()
    let items = listOfWords.getElementsByTagName('li')
    if(items.length==0){
      for(var i = 0; i<5; i++){
        listOfWords.appendChild(document.createElement('li'))
      }
    }
    for(var i = 0; i<5; i++)  {
      let item = items[i]
      item.innerHTML = list[i]
    }
  }




