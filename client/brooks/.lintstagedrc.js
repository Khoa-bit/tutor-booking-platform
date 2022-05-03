module.exports = {
  "*.{js,jsx,ts,tsx}": ["next lint --fix --file"],
  "*.{js,jsx,ts,tsx,html,scss,css,md}": ["prettier --write"],
};
