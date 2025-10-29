import matplotlib.pyplot as plt
import matplotlib.patches as patches
import matplotlib.animation as animation

# Create figure and axis
fig, ax = plt.subplots()
ax.set_xlim(0, 100)
ax.set_ylim(0, 50)
ax.set_aspect('equal')
ax.set_title("2D Car Animation using Transformation")

# Car body (rectangle)
car_body = patches.Rectangle((0, 10), 20, 10, fc='blue')

# Wheels (circles)
wheel1 = patches.Circle((5, 8), 2, fc='black')
wheel2 = patches.Circle((15, 8), 2, fc='black')

# Add shapes to the plot
ax.add_patch(car_body)
ax.add_patch(wheel1)
ax.add_patch(wheel2)

# Animation function — moves the car
def animate(frame):
    # Translation in x-direction
    x_shift = frame % 100  # wrap around after 100
    car_body.set_x(x_shift)
    wheel1.center = (x_shift + 5, 8)
    wheel2.center = (x_shift + 15, 8)
    return car_body, wheel1, wheel2

# Create animation
ani = animation.FuncAnimation(fig, animate, frames=200, interval=50, blit=True)

plt.show()
