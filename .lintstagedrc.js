export default {
  '{src/test/javascript,src/main/webapp}/**/*.{js,ts,tsx,vue}': ['eslint --fix'],
  'src/main/style/**/*.{css,scss}': ['stylelint --fix', 'prettier --write'],
  'src/main/glyph/**/*.{css,scss}': ['prettier --write'],
  'src/main/style/**/*.pug': ['eslint --fix'],
  '{{src/**/,}*.{md,json,yml,html,java,xml,feature},*.{js,cjs,ts},.github/**/*.yml,documentation/**/*.md}': ['prettier --write'],
};
