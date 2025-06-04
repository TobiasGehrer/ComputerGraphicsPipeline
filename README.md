# Simple Computer Graphics Pipeline

This project implements a basic computer graphics rendering pipeline using JavaFX. It supports both push- and pull-based architectures for rendering a 3D model (e.g., a teapot) in various styles such as point, wireframe, filled, and flat shaded.

## Features

- **Model-View Transformation**
- **Backface Culling**
- **Depth Sorting**
- **Coloring and Lighting (Flat Shading)**
- **Projection and Perspective Division**
- **Screen Space Mapping**
- **Rendering (Point, Wireframe, Filled)**

## Pipeline Modes

You can switch between **push** and **pull** pipeline architectures using the `USE_PUSH_PIPELINE` flag in `Main.java`:
```java
private final static boolean USE_PUSH_PIPELINE = false;
```

## Directory Structure

- `at.fhv.sysarch.lab3.obj` - OBJ model loading and face representation
- `at.fhv.sysarch.lab3.pipeline` - Pipeline factories and data structures
- `at.fhv.sysarch.lab3.pipeline.push` - Push pipeline filter implementations
- `at.fhv.sysarch.lab3.pipeline.pull` - Pull pipeline filter implementations
- `at.fhv.sysarch.lab3.pipeline.util` - Shared utility functions
- `at.fhv.sysarch.lab3.rendering` - Rendering modes and configuration

## Rendering Modes

You can select different rendering styles for each viewport:
- `POINT` - Single pixels at vertex locations
- `WIREFRAME` - Only triangle edges
- `FILLED` - Solid triangles (optionally with lighting)

## Lighting

Flat shading is supported using a simple diffuse lighting model. It computes the dot product between the face normal and light direction to modulate color brightness.

## Utility Classes

Common logic (e.g., rendering, transformation, lighting calculations) is extracted into utility classes like:
- `RenderingUtil`
- `LightingUtil`
- `BackfaceCullingUtil`
- `MatrixUtils`, `TransformUtil`, etc.

This helps reduce code duplication across push and pull implementations.

## How to Run

1. Ensure JavaFX is configured correctly.
2. Place your OBJ model in the `resources` folder (e.g., `teapot.obj`).
3. Run `Main.java`.

You will see four viewports with different rendering configurations.
