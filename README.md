# game-of-life

This implementation uses the following entities as a model:

Cell - a two dimensional point, describing the cell's location in orthogonal grid,
Generation - a collection (Set) of populated cells only,
Lights - an auxiliary object, a field containing the cells with at least one neighbor.
Rule - a rule to apply determining the 'populated state' of a cell for the next generation.

The algorithm consists of two phases:
1) Calculating the number of neighbors for relevant cells (obtaining the Lights field)
2) Applying the set of Rules (for Lights field while using the Generation's data)

  For calculating the number of neighbors (each relevant cell contains)
we consider an alive cell as an entity, that enlights all its neighbors
sparking a single unit of light.
   While 'sparking' all the cells of a generation we obtain the field (Lights),
describing the number of neighbors each relevant cell contains in an efficient way.