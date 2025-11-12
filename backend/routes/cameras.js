const router = require('express').Router();
const Camera = require('../models/Camera');
const auth = require('../middleware/auth');

// Get all cameras
router.get('/', async (req, res) => {
  try {
    const cameras = await Camera.find();
    res.json(cameras);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Get single camera
router.get('/:id', async (req, res) => {
  try {
    const camera = await Camera.findById(req.params.id);
    if (!camera) {
      return res.status(404).json({ message: 'Camera not found' });
    }
    res.json(camera);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Add new camera (admin only)
router.post('/', auth, async (req, res) => {
  try {
    const { name, brand, model, description, price, rentalPrice, imageUrl } = req.body;

    const newCamera = new Camera({
      name,
      brand,
      model,
      description,
      price,
      rentalPrice,
      imageUrl
    });

    const savedCamera = await newCamera.save();
    res.status(201).json(savedCamera);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Update camera (admin only)
router.put('/:id', auth, async (req, res) => {
  try {
    const updatedCamera = await Camera.findByIdAndUpdate(
      req.params.id,
      req.body,
      { new: true }
    );
    res.json(updatedCamera);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Delete camera (admin only)
router.delete('/:id', auth, async (req, res) => {
  try {
    await Camera.findByIdAndDelete(req.params.id);
    res.json({ message: 'Camera deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

module.exports = router;