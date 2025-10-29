import matplotlib.pyplot as plt
import numpy as np

# Function to plot object
def plot_object(points, label, color):
    points = np.append(points, [points[0]], axis=0)  # to close the shape
    plt.plot(points[:, 0], points[:, 1], color, label=label)

# Original coordinates of a triangle
points = np.array([[1, 1], [4, 1], [2.5, 4]])

# -----------------------------
# 1️⃣ Translation
# -----------------------------
tx, ty = 2, 3   # translation factors
translated_points = points + np.array([tx, ty])

# -----------------------------
# 2️⃣ Rotation
# -----------------------------
theta = np.radians(45)  # rotation angle in degrees
rotation_matrix = np.array([[np.cos(theta), -np.sin(theta)],
                            [np.sin(theta),  np.cos(theta)]])
rotated_points = np.dot(points, rotation_matrix)

# -----------------------------
# 3️⃣ Scaling
# -----------------------------
sx, sy = 2, 1.5   # scaling factors
scaling_matrix = np.array([[sx, 0],
                           [0, sy]])
scaled_points = np.dot(points, scaling_matrix)

# -----------------------------
# Plot all transformations
# -----------------------------
plt.figure(figsize=(7, 7))
plot_object(points, 'Original Object', 'b')
plot_object(translated_points, 'Translated', 'r')
plot_object(rotated_points, 'Rotated', 'g')
plot_object(scaled_points, 'Scaled', 'm')

plt.title('2D Transformations: Translation, Rotation & Scaling')
plt.legend()
plt.grid(True)
plt.axis('equal')
plt.show()
