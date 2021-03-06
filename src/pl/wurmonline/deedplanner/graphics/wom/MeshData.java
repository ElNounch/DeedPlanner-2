package pl.wurmonline.deedplanner.graphics.wom;

import javax.media.opengl.GL2;
import pl.wurmonline.deedplanner.util.DeedPlannerException;

public class MeshData {
    
    private String name;
    
    private float[] vertices;
    private float[] normals;
    private float[] texcoords;
    private int[] triangles;
    
    MeshData() {
        
    }
    
    public int createModel(GL2 g) throws DeedPlannerException {
        if (!isValid()) {
            throw new DeedPlannerException("Invalid model");
        }
        
        int list = g.glGenLists(1);
        g.glNewList(list, GL2.GL_COMPILE);
            g.glPushMatrix();
                g.glRotatef(90, 1, 0, 0);
                g.glBegin(GL2.GL_TRIANGLES);
                    for (int i=0; i<triangles.length; i++) {
                        int point = triangles[i];
                        int vertLoc = point * 3;
                        int normalLoc = point * 3;
                        int textureLoc = point * 2;

                        g.glTexCoord2f(texcoords[textureLoc], 1 - texcoords[textureLoc+1]);
                        g.glNormal3f(normals[normalLoc], normals[normalLoc+1], normals[normalLoc+2]);
                        g.glVertex3f(vertices[vertLoc], vertices[vertLoc+1], vertices[vertLoc+2]);
                    }
                g.glEnd();
            g.glPopMatrix();
        g.glEndList();
        
        return list;
    }
    
    public boolean isValid() {
        return vertices!=null && texcoords!=null && triangles!=null;
    }
    
    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }
    
    public void setNormals(float[] normals) {
        this.normals = normals;
    }
    
    public void setTexcoords(float[] texcoords) {
        this.texcoords = texcoords;
    }
    
    public void setTriangles(int[] triangles) {
        this.triangles = triangles;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name;
    }
}