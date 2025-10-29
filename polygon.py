import matplotlib.pyplot as plt

# DDA Line Drawing Function
def dda_line(x1, y1, x2, y2):
    dx = x2 - x1
    dy = y2 - y1

    steps = int(max(abs(dx), abs(dy)))
    x_inc = dx / steps
    y_inc = dy / steps

    x = x1
    y = y1

    x_points = []
    y_points = []

    for _ in range(steps + 1):
        x_points.append(round(x))
        y_points.append(round(y))
        x += x_inc
        y += y_inc

    plt.plot(x_points, y_points, color='blue')

# Draw Polygon
def draw_polygon(points):
    n = len(points)
    for i in range(n):
        x1, y1 = points[i]
        x2, y2 = points[(i + 1) % n]  # Connect last point to first
        dda_line(x1, y1, x2, y2)

    plt.title("Polygon Drawing using DDA Algorithm")
    plt.xlabel("X-axis")
    plt.ylabel("Y-axis")
    plt.grid(True)
    plt.show()

# Input polygon vertices
n = int(input("Enter number of vertices: "))
points = []

print("Enter the coordinates (x y) of each vertex:")
for i in range(n):
    x, y = map(int, input(f"Vertex {i+1}: ").split())
    points.append((x, y))

draw_polygon(points)
