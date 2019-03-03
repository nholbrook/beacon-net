let gulp = require("gulp");
let sass = require("gulp-sass");
let autoprefixer = require("gulp-autoprefixer");
let concat = require("gulp-concat");
let uglify = require('gulp-uglify-es').default;
let csso = require('gulp-csso');
let rename = require('gulp-rename');
var browserSync = require('browser-sync').create();

var paths = {
  styles: {
    src: "src/main/resources/static/css/src/**/*.scss",
    dest: "src/main/resources/static/css/dist"
},
  scripts: {
    src: "src/main/resources/static/js/src/**/*.js",
    dest: "src/main/resources/static/js/dist"
  }
};

function style() {
  return (
    gulp
      .src(paths.styles.src)
      .pipe(sass())
      .on("error", sass.logError)
      .pipe(autoprefixer())
      .pipe(gulp.dest(paths.styles.dest))
      .pipe(csso())
      .pipe(rename({extname: '.min.css'}))
      .pipe(gulp.dest(paths.styles.dest))
  );
}

function script() {
    return (
        gulp
            .src(paths.scripts.src)
            .pipe(gulp.dest(paths.scripts.dest))
            .pipe(uglify())
            .pipe(rename({extname: '.min.js'}))
            .pipe(gulp.dest(paths.scripts.dest))
    );
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
