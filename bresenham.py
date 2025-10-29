import matplotlib.pyplot as plt

def bresenham_line(x1, y1, x2, y2):
    # Calculate dx, dy
    dx = abs(x2 - x1)
    dy = abs(y2 - y1)
    
    # Determine the direction of increment
    sx = 1 if x1 < x2 else -1
    sy = 1 if y1 < y2 else -1
    
    # Initialize the error term
    err = dx - dy

    x_points = []
    y_points = []

    # Loop until end point is reached
    while True:
        x_points.append(x1)
        y_points.append(y1)

        if x1 == x2 and y1 == y2:
            break

        e2 = 2 * err

        if e2 > -dy:
            err -= dy
            x1 += sx

        if e2 < dx:
            err += dx
            y1 += sy

    # Plot the line
    plt.plot(x_points, y_points, color='red', marker='o')
    plt.title("Bresenham Line Drawing Algorithm")
    plt.xlabel("X-axis")
    plt.ylabel("Y-axis")
    plt.grid(True)
    plt.show()


# Example input
x1, y1 = map(int, input("Enter x1 y1: ").split())
x2, y2 = map(int, input("Enter x2 y2: ").split())

bresenham_line(x1, y1, x2, y2)
