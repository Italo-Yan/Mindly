const express = require('express')
const app = express();

app.get('/', (req, res) => {
  res.send("Hello World from Express!")
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));