import matplotlib.pyplot as plt

def dda_line(x1, y1, x2, y2):
    # Calculate dx, dy
    dx = x2 - x1
    dy = y2 - y1

    # Find number of steps
    steps = int(max(abs(dx), abs(dy)))

    # Calculate increment values for each step
    x_inc = dx / steps
    y_inc = dy / steps

    # Initialize starting point
    x = x1
    y = y1

    # Lists to store plotted points
    x_points = [x]
    y_points = [y]

    # Generate intermediate points
    for _ in range(steps):
        x += x_inc
        y += y_inc
        x_points.append(round(x))
        y_points.append(round(y))

    # Plot the points
    plt.plot(x_points, y_points, color='blue', marker='o')
    plt.title("DDA Line Drawing Algorithm")
    plt.xlabel("X-axis")
    plt.ylabel("Y-axis")
    plt.grid(True)
    plt.show()


# Example input (you can change these values)
x1, y1 = map(int, input("Enter x1 y1: ").split())
x2, y2 = map(int, input("Enter x2 y2: ").split())

dda_line(x1, y1, x2, y2)
