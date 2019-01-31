$(document).ready(function(){

  var switchElement = $('#switchTheme');

  var currentTheme = localStorage.getItem('theme');
  if (currentTheme === 'default') {
    document.documentElement.setAttribute('data-theme','default');
    switchElement.text('Dark theme');
  } else {
    document.documentElement.setAttribute('data-theme','dark');
    switchElement.text('Light theme');
  }

  switchElement.click(function(){
    switchTheme();
  });

  function switchTheme() {
    var currentTheme = localStorage.getItem('theme');
    if (currentTheme === 'default') {
      document.documentElement.setAttribute('data-theme','dark');
      switchElement.text('Light theme');
      localStorage.setItem('theme', 'dark');
    } else {
      document.documentElement.setAttribute('data-theme','default');
      switchElement.text('Dark theme');
      localStorage.setItem('theme', 'default');
    }
  }
});
