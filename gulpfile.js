let gulp = require("gulp");
let sass = require("gulp-sass");
let autoprefixer = require("gulp-autoprefixer");
let concat = require("gulp-concat");
let uglify = require('gulp-uglify-es').default;
let csso = require('gulp-csso');
let rename = require('gulp-rename');
let browserSync = require('browser-sync').create();
let htmlbeautify = require('gulp-html-beautify');

var paths = {
  styles: {
    src: "src/main/resources/static/css/src/**/*.scss",
    dest: "src/main/resources/static/css/dist"
  },
  scripts: {
    src: "src/main/resources/static/js/src/**/*.js",
    dest: "src/main/resources/static/js/dist"
  },
  templates: {
    src: "src/main/resources/templates/*.html",
    dest: "src/main/resources/templates"
  }
};

function style() {
  return (
    gulp
      .src(paths.styles.src)
      .pipe(sass())
      .pipe(autoprefixer())
      .pipe(csso())
      .pipe(rename({extname: '.min.css'}))
      .pipe(gulp.dest(paths.styles.dest))
  );
}

function script() {
    return (
        gulp
            .src(paths.scripts.src)
            .pipe(uglify())
            .pipe(rename({extname: '.min.js'}))
            .pipe(gulp.dest(paths.scripts.dest))
    );
}

function beautify(){
  var options = {
    "indent_size": 4,
    "indent_with_tabs": true
  };
  return gulp.src(paths.templates.src)
    .pipe(htmlbeautify(options))
    .pipe(gulp.dest(paths.templates.dest));
}

function watch(){
  gulp.watch(paths.styles.src, style);
  gulp.watch(paths.scripts.src, script);
}

function serve(){
  watch();
  browserSync.init({
      server: {
        baseDir: ['src/main/resources/templates', 'src/main/resources/static']
      }
  });
}

exports.style = style;
exports.script = script;
exports.watch = watch;
exports.serve = serve;
exports.beautify = beautify;
